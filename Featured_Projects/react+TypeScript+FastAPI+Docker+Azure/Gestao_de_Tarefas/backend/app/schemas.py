from datetime import datetime

from pydantic import BaseModel, ConfigDict, Field


class TaskBase(BaseModel):
    title: str
    description: str | None = None


class TaskCreate(TaskBase):
    title: str = Field(min_length=3, max_length=100)


class TaskUpdate(BaseModel):
    title: str | None = None
    description: str | None = None
    completed: bool | None = None


class TaskResponse(TaskBase):
    id: int
    completed: bool
    created_at: datetime

    model_config = ConfigDict(from_attributes=True)
