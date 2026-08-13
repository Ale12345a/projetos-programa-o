import sqlite3

# Conectar ou criar banco de dados
conn = sqlite3.connect('database.db')
cursor = conn.cursor()

# Criar tabela de livros
cursor.execute('''
CREATE TABLE IF NOT EXISTS books (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    author TEXT NOT NULL,
    year INTEGER,
    available BOOLEAN DEFAULT 1
)
''')
conn.commit()

# Funções do sistema
def add_book(title, author, year):
    cursor.execute('INSERT INTO books (title, author, year) VALUES (?, ?, ?)', (title, author, year))
    conn.commit()
    print(f'Livro "{title}" adicionado!')

def list_books():
    cursor.execute('SELECT * FROM books')
    books = cursor.fetchall()
    print("\n--- Livros na Biblioteca ---")
    for book in books:
        status = "Disponível" if book[4] else "Indisponível"
        print(f"{book[0]} - {book[1]} por {book[2]} ({book[3]}) - {status}")

def borrow_book(book_id):
    cursor.execute('SELECT available FROM books WHERE id = ?', (book_id,))
    result = cursor.fetchone()
    if result and result[0]:
        cursor.execute('UPDATE books SET available = 0 WHERE id = ?', (book_id,))
        conn.commit()
        print("Livro emprestado com sucesso!")
    else:
        print("Livro não disponível ou não existe.")

def return_book(book_id):
    cursor.execute('UPDATE books SET available = 1 WHERE id = ?', (book_id,))
    conn.commit()
    print("Livro devolvido com sucesso!")

# Menu interativo
def menu():
    while True:
        print("\n1 - Adicionar livro")
        print("2 - Listar livros")
        print("3 - Emprestar livro")
        print("4 - Devolver livro")
        print("5 - Sair")
        choice = input("Escolha uma opção: ")
        
        if choice == '1':
            title = input("Título: ")
            author = input("Autor: ")
            year = int(input("Ano: "))
            add_book(title, author, year)
        elif choice == '2':
            list_books()
        elif choice == '3':
            book_id = int(input("ID do livro: "))
            borrow_book(book_id)
        elif choice == '4':
            book_id = int(input("ID do livro: "))
            return_book(book_id)
        elif choice == '5':
            print("Saindo...")
            break
        else:
            print("Opção inválida!")

if __name__ == "__main__":
    menu()