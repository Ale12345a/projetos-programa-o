class QueueError(IndexError):
    pass


class Queue:
    def __init__(self):
        self.queue_list = []

    def put(self, element):
        self.queue_list.insert(0, element)

    def get(self):
        if self.queue_list:
            element = self.queue_list.pop()
            return element
        else:
            raise QueueError


queue = Queue()

queue.put(1)
queue.put("dog")
queue.put(False)

try:
    for i in range(4):
        print(queue.get())
except QueueError:
    print("Erro de fila")