class Top:
    def m_top(self):
        print("top")


class Middle_Left(Top):
    def m_middle(self):
        print("middle_left")


class Middle_Right(Top):
    def m_middle(self):
        print("middle_right")


class Bottom(Middle_Left, Middle_Right):
    def m_bottom(self):
        print("bottom")


obj = Bottom()

obj.m_bottom()
obj.m_middle()
obj.m_top()

print("\nMRO:")
for cls in Bottom.__mro__:
    print(cls.__name__)