const db = require("../database/db");

const getAllTasks = (callback) => {
  db.all("SELECT * FROM tasks", [], callback);
};

const createTask = (title, callback) => {
  db.run(
    "INSERT INTO tasks (title, completed) VALUES (?, ?)",
    [title, 0],
    callback
  );
};

const updateTask = (id, completed, callback) => {
  db.run(
    "UPDATE tasks SET completed = ? WHERE id = ?",
    [completed, id],
    callback
  );
};

const deleteTask = (id, callback) => {
  db.run("DELETE FROM tasks WHERE id = ?", [id], callback);
};

module.exports = {
  getAllTasks,
  createTask,
  updateTask,
  deleteTask
};