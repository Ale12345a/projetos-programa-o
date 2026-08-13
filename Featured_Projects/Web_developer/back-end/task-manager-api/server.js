const express = require("express");
const app = express();
const taskRoutes = require("./routes/tasks");

require("./database/db");

app.use(express.json());

app.use("/tasks", taskRoutes);

app.listen(3001, () => {
  console.log("Server running on port 3001");
});