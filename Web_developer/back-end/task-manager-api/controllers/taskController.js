const taskModel = require("../models/taskModel");

exports.getTasks = (req, res) => {
  taskModel.getAllTasks((err, rows) => {
    if (err) return res.status(500).json(err);
    res.json(rows);
  });
};

exports.createTask = (req, res) => {
  const { title } = req.body;

  taskModel.createTask(title, (err) => {
    if (err) return res.status(500).json(err);
    res.json({ message: "Task created" });
  });
};

exports.updateTask = (req, res) => {
  const { completed } = req.body;
  const id = req.params.id;

  taskModel.updateTask(id, completed, (err) => {
    if (err) return res.status(500).json(err);
    res.json({ message: "Task updated" });
  });
};

exports.deleteTask = (req, res) => {
  const id = req.params.id;

  taskModel.deleteTask(id, (err) => {
    if (err) return res.status(500).json(err);
    res.json({ message: "Task deleted" });
  });
};