class WeekDayError(Exception):
    pass


class Weeker:
    __days = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']

    def __init__(self, day):
        if day not in Weeker.__days:
            raise WeekDayError

        self.__day = Weeker.__days.index(day)

    def __str__(self):
        return Weeker.__days[self.__day]

    def add_days(self, n):
        self.__day = (self.__day + n) % 7

    def subtract_days(self, n):
        self.__day = (self.__day - n) % 7


try:
    weekday = Weeker('Mon')
    print(weekday)
    weekday.add_days(15)
    print(weekday)
    weekday.subtract_days(23)
    print(weekday)
    weekday = Weeker('Monday')
except WeekDayError:
    print("Sorry, I can't serve your request.")