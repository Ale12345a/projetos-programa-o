from datetime import date
from typing import Literal

from pydantic import BaseModel, Field


class TransactionBase(BaseModel):
    description: str = Field(min_length=1)
    amount: float = Field(gt=0)
    type: Literal["income", "expense"]
    category: str = Field(min_length=1)
    date: date


class TransactionCreate(TransactionBase):
    pass


class TransactionResponse(TransactionBase):
    id: int

    class Config:
        from_attributes = True