from fastapi import FastAPI, Depends, HTTPException
from sqlalchemy.orm import Session
import models, schemas, crud
from database import SessionLocal, engine, Base

# Criar tabelas
Base.metadata.create_all(bind=engine)

app = FastAPI(title="API de Usuários e Tarefas")

# Dependência
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# Criar usuário
@app.post("/users/", response_model=schemas.User)
def create_user(user: schemas.UserCreate, db: Session = Depends(get_db)):
    db_user = crud.get_user(db, user.username)
    if db_user:
        raise HTTPException(status_code=400, detail="Usuário já existe")
    return crud.create_user(db, user)

# Criar tarefa
@app.post("/users/{user_id}/tasks/", response_model=schemas.Task)
def create_task_for_user(user_id: int, task: schemas.TaskCreate, db: Session = Depends(get_db)):
    return crud.create_task(db, task, user_id)

# Listar tarefas
@app.get("/users/{user_id}/tasks/", response_model=list[schemas.Task])
def read_tasks(user_id: int, db: Session = Depends(get_db)):
    return crud.get_tasks(db, user_id)