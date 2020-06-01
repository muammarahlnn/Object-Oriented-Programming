class Menu :

    def __init__(self, text) :
        self.__file = text
        self.__ls_Menu = []
        self.set_ls_menu()

    def get_lsMenu(self) :
        return self.__ls_Menu

    def index_lsMenu(self, i, j) :
        return self.get_lsMenu()[i][j]

    def set_ls_menu(self) :
        with open(self.__file) as f :
            data = f.readlines()
            data = [d.replace("\n", "") for d in data]

            for d in data : 
                temp_d = d.split(";")
                temp_menu = [temp_d[0], int(temp_d[1].replace(".", ""))]
                self.__ls_Menu.append(temp_menu) 

    def show_menu(self, obj) :
        title = "Sistem Penjualan Hamburger"
        print("+" + "=" * 48 + "+")
        print(f"|{title:^48}|")
        print("+" + "=" * 48 + "+")
        print(f"|{obj :^48}|")
        print(f"|{'':^48}|")

        index = 1
        for m in self.__ls_Menu :
            # harga = 
            menu = (
                f"{str(index) + '. ':>3}" 
                f"{m[0]:<25}"
                f"{'Rp. ' + str(m[1]):<10}"
            )
            print(f"|{menu :^48}|")
            
            index += 1

        print("+" + "=" * 48 + "+")
        return index

class Addition(Menu) :
    
    def __init__(self) :
        super().__init__("Text/listAddition.txt")

    def __repr__(self) :
        return "Addition"

class Hamburger(Menu) :
    
    def __init__(self) :
        super().__init__("Text/listHamburger.txt")

    def __repr__(self) :
        return "Burger"