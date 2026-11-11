import calendar

class MyCalendar(calendar.Calendar):
    def count_weekday_in_year(self, year, weekday):
        count = 0
        for month in range(1, 13):
            weeks = self.monthdays2calendar(year, month)
            for week in weeks:
                for day, weekday_number in week:
                    if day != 0 and weekday_number == weekday:
                        count += 1
        return count

my_calendar = MyCalendar()
print(my_calendar.count_weekday_in_year(2019, 0))
print(my_calendar.count_weekday_in_year(2000, 6))