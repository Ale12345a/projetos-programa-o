-- Tabela de Professores (1:N com Cursos)
CREATE TABLE IF NOT EXISTS Professor (
    id INT PRIMARY KEY,
    nome VARCHAR(50),
    disciplina VARCHAR(50)
);

-- Tabela de Cursos (cada curso tem um professor)
CREATE TABLE IF NOT EXISTS Curso (
    id INT PRIMARY KEY,
    nome VARCHAR(50),
    professor_id INT,
    FOREIGN KEY (professor_id) REFERENCES Professor(id)
);

-- Tabela de Alunos
CREATE TABLE IF NOT EXISTS Aluno (
    id INT PRIMARY KEY,
    nome VARCHAR(50),
    idade INT
);

-- Tabela de ligação N:M entre Alunos e Cursos
CREATE TABLE IF NOT EXISTS AlunoCurso (
    id INT PRIMARY KEY,
    aluno_id INT,
    curso_id INT,
    nota_final DECIMAL(4,2),
    FOREIGN KEY (aluno_id) REFERENCES Aluno(id),
    FOREIGN KEY (curso_id) REFERENCES Curso(id)
);

-- Professores
INSERT OR IGNORE INTO Professor (id, nome, disciplina) VALUES
(1, 'Ana', 'Matemática'),
(2, 'Bruno', 'Física'),
(3, 'Carla', 'História');

-- Cursos
INSERT OR IGNORE INTO Curso (id, nome, professor_id) VALUES
(1, 'Álgebra', 1),
(2, 'Cálculo', 1),
(3, 'Mecânica', 2),
(4, 'História Moderna', 3);

-- Alunos
INSERT OR IGNORE INTO Aluno (id, nome, idade) VALUES
(1, 'Alice', 18),
(2, 'Bruno', 19),
(3, 'Carla', 18),
(4, 'Daniel', 20),
(5, 'Eva', 17);

-- AlunoCurso (N:M)
INSERT OR IGNORE INTO AlunoCurso (id, aluno_id, curso_id, nota_final) VALUES
(1, 1, 1, 18.5),  -- Alice no Álgebra
(2, 1, 2, 17.0),  -- Alice no Cálculo
(3, 2, 1, 16.0),  -- Bruno no Álgebra
(4, 2, 3, 15.5),  -- Bruno na Mecânica
(5, 3, 4, 19.0),  -- Carla em História Moderna
(6, 4, 3, 14.0),  -- Daniel em Mecânica
(7, 5, 1, 20.0),  -- Eva no Álgebra
(8, 5, 4, 18.0);  -- Eva em História Moderna

--Exercício 1 – 1:N:
SELECT c.nome AS curso, p.nome AS professor
FROM Curso c
JOIN Professor p ON c.professor_id = p.id; --Mostrar todos os cursos com o nome do professor que leciona:

--Exercício 2 – N:M:
SELECT a.nome AS aluno, c.nome AS curso
FROM Aluno a
JOIN AlunoCurso ac ON a.id = ac.aluno_id
JOIN Curso c ON ac.curso_id = c.id
ORDER BY a.nome; --Mostrar todos os alunos com os cursos em que estão matriculados:

--exercicio 3:
SELECT a.nome, AVG(ac.nota_final) AS media_notas
FROM Aluno a
JOIN AlunoCurso ac ON a.id = ac.aluno_id
GROUP BY a.nome; --Mostrar a média de notas de cada aluno:

--exercicio 4:
SELECT c.nome AS curso, COUNT(ac.aluno_id) AS total_alunos
FROM Curso c
JOIN AlunoCurso ac ON c.id = ac.curso_id
GROUP BY c.nome
ORDER BY total_alunos DESC
LIMIT 1; --Mostrar qual curso tem mais alunos inscritos:

--exercicio 5:
SELECT DISTINCT a.nome AS aluno, c.nome AS curso
FROM Aluno a
JOIN AlunoCurso ac ON a.id = ac.aluno_id
JOIN Curso c ON ac.curso_id = c.id
JOIN Professor p ON c.professor_id = p.id
WHERE p.disciplina = 'Matemática'; --Mostrar alunos que estão em cursos da disciplina "Matemática" (usando o professor como referência):

--resultado:
--curso             professor
----------------  ---------
--Álgebra           Ana
--Cálculo           Ana
--Mecânica          Bruno
--História Moderna  Carla

--aluno   curso
------  ----------------
--Alice   Álgebra
--Alice   Cálculo
--Bruno   Álgebra
--Bruno   Mecânica
--Carla   História Moderna
--Daniel  Mecânica
--Eva     Álgebra
--Eva     História Moderna

--nome    media_notas
------  -----------
--Alice   17.75
--Bruno   15.75
--Carla   19.0
--Daniel  14.0
--Eva     19.0

--curso    total_alunos
-------  ------------
--Álgebra  3
