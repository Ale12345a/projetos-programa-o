from fastapi import Depends, FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy.orm import Session
from sqlalchemy import func

from .database import Base, engine, get_db
from . import models, schemas

Base.metadata.create_all(bind=engine)


app = FastAPI(
    title="Gestor Financeiro API",
    description="API para gerir movimentos financeiros",
    version="1.0.0"
)

origins = [
    "http://localhost:5173",
    "http://127.0.0.1:5173",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/")
def root():
    return {
        "message": "Gestor Financeiro API está a funcionar!"
    }


# Criar movimento
@app.post("/transactions", response_model=schemas.TransactionResponse)
def create_transaction(
    transaction: schemas.TransactionCreate,
    db: Session = Depends(get_db)
):
    db_transaction = models.Transaction(
        description=transaction.description,
        amount=transaction.amount,
        type=transaction.type,
        category=transaction.category,
        date=transaction.date
    )

    db.add(db_transaction)
    db.commit()
    db.refresh(db_transaction)

    return db_transaction


# Listar todos os movimentos
@app.get("/transactions", response_model=list[schemas.TransactionResponse])
def get_transactions(
    type: str | None = None,
    category: str | None = None,
    month: str | None = None,
    db: Session = Depends(get_db)
):
    query = db.query(models.Transaction)

    if type is not None:
        query = query.filter(models.Transaction.type == type)

    if category is not None:
        query = query.filter(models.Transaction.category == category)

    if month is not None:
        try:
            year, month_number = month.split("-")

            year = int(year)
            month_number = int(month_number)

            if month_number < 1 or month_number > 12:
                raise ValueError

        except ValueError:
            raise HTTPException(
                status_code=400,
                detail="O mês deve estar no formato YYYY-MM, por exemplo 2026-09"
            )

        query = query.filter(
            models.Transaction.date >= f"{year}-{month_number:02d}-01"
        )

        if month_number == 12:
            next_year = year + 1
            next_month = 1
        else:
            next_year = year
            next_month = month_number + 1

        query = query.filter(
            models.Transaction.date < f"{next_year}-{next_month:02d}-01"
        )

    transactions = query.order_by(
        models.Transaction.date.desc()
    ).all()

    return transactions


# Resumo financeiro
# Esta rota tem de estar antes de /transactions/{transaction_id}
@app.get("/transactions/summary")
def get_summary(db: Session = Depends(get_db)):
    transactions = db.query(models.Transaction).all()

    total_income = sum(
        transaction.amount
        for transaction in transactions
        if transaction.type == "income"
    )

    total_expense = sum(
        transaction.amount
        for transaction in transactions
        if transaction.type == "expense"
    )

    balance = total_income - total_expense

    return {
        "total_income": total_income,
        "total_expense": total_expense,
        "balance": balance
    }

@app.get("/transactions/categories")
def get_expenses_by_category(db: Session = Depends(get_db)):
    results = (
        db.query(
            models.Transaction.category,
            func.sum(models.Transaction.amount).label("amount")
        )
        .filter(models.Transaction.type == "expense")
        .group_by(models.Transaction.category)
        .all()
    )

    return [
        {
            "category": category,
            "amount": amount
        }
        for category, amount in results
    ]


# Obter um movimento pelo ID
@app.get("/transactions/{transaction_id}", response_model=schemas.TransactionResponse)
def get_transaction(
    transaction_id: int,
    db: Session = Depends(get_db)
):
    transaction = db.query(models.Transaction).filter(
        models.Transaction.id == transaction_id
    ).first()

    if transaction is None:
        raise HTTPException(
            status_code=404,
            detail="Movimento não encontrado"
        )

    return transaction


# Atualizar um movimento
@app.put("/transactions/{transaction_id}", response_model=schemas.TransactionResponse)
def update_transaction(
    transaction_id: int,
    transaction: schemas.TransactionCreate,
    db: Session = Depends(get_db)
):
    existing_transaction = db.query(models.Transaction).filter(
        models.Transaction.id == transaction_id
    ).first()

    if existing_transaction is None:
        raise HTTPException(
            status_code=404,
            detail="Movimento não encontrado"
        )

    existing_transaction.description = transaction.description
    existing_transaction.amount = transaction.amount
    existing_transaction.type = transaction.type
    existing_transaction.category = transaction.category
    existing_transaction.date = transaction.date

    db.commit()
    db.refresh(existing_transaction)

    return existing_transaction


# Eliminar um movimento
@app.delete("/transactions/{transaction_id}")
def delete_transaction(
    transaction_id: int,
    db: Session = Depends(get_db)
):
    transaction = db.query(models.Transaction).filter(
        models.Transaction.id == transaction_id
    ).first()

    if transaction is None:
        raise HTTPException(
            status_code=404,
            detail="Movimento não encontrado"
        )

    db.delete(transaction)
    db.commit()

    return {
        "message": "Movimento eliminado com sucesso"
    }
