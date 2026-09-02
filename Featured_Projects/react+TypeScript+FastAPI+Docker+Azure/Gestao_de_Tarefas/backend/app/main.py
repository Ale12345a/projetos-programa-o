from fastapi import Depends, FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy.orm import Session

from .database import Base, engine, get_db
from .models import Task
from .schemas import TaskCreate, TaskResponse, TaskUpdate


app = FastAPI()
Base.metadata.create_all(bind=engine)


# Permitir pedidos vindos do frontend React
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:5173"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.get("/tasks", response_model=list[TaskResponse])
def get_tasks(db: Session = Depends(get_db)):
    return db.query(Task).all()


@app.post("/tasks", response_model=TaskResponse, status_code=201)
def create_task(task: TaskCreate, db: Session = Depends(get_db)):
    new_task = Task(
        title=task.title,
        description=task.description
    )

    db.add(new_task)
    db.commit()
    db.refresh(new_task)

    return new_task


@app.patch("/tasks/{task_id}", response_model=TaskResponse)
def update_task(
    task_id: int,
    task: TaskUpdate,
    db: Session = Depends(get_db)
):
    existing_task = db.query(Task).filter(Task.id == task_id).first()

    if existing_task is None:
        raise HTTPException(status_code=404, detail="Tarefa não encontrada")

    if task.title is not None:
        existing_task.title = task.title

    if task.description is not None:
        existing_task.description = task.description

    if task.completed is not None:
        existing_task.completed = task.completed

    db.commit()
    db.refresh(existing_task)

    return existing_task


@app.delete("/tasks/{task_id}")
def delete_task(
    task_id: int,
    db: Session = Depends(get_db)
):
    existing_task = db.query(Task).filter(Task.id == task_id).first()

    if existing_task is None:
        raise HTTPException(status_code=404, detail="Tarefa não encontrada")

    db.delete(existing_task)
    db.commit()

    return {"message": "Tarefa eliminada com sucesso"}