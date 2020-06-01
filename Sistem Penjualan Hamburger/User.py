from Menu import *
import Utilities as util
import sys

class User :
    
    def __init__(self) :
        self.__ls_pesanan_burger = []
        self.__ls_pesanan_addition = []

    def add_pesanan_burger(self, pesanan) :
        self.__ls_pesanan_burger.append(pesanan)

    def add_pesanan_addition(self, pesanan) :
        self.__ls_pesanan_addition.append(pesanan)

    def get_lsPesanan_burger(self) :
        return self.__ls_pesanan_burger

    def get_lsPesanan_addition(self) :
        return self.__ls_pesanan_addition

    def index_lsPesanan_burger(self, i, j) :
        return self.__ls_pesanan_burger[i][j]

    def index_lsPesanan_addition(self, i, j) :
        return self.__ls_pesanan_addition[i][j]

        
    def confirm_burger(self) :
        burger = Hamburger()
        confirm_burger = (
            util.getYesOrNo(
                f"Apakah anda yakin ingin memesan {util.input_banyak_burger}"
                f" {burger.index_lsMenu(util.input_burger-1, 0)}?"
            )
        )

        while confirm_burger == False :
            temp_confirm = util.getYesOrNo("Apakah anda masih ingin memesan burger?")
            if temp_confirm == False :
                title = "Sistem Penjualan Hamburger"
                print("+" + "=" * 48 + "+")
                print(f"|{title:^48}|")
                print("+" + "=" * 48 + "+")
                print(f"|{'':^48}|")
                print(f"|{'Sayang sekali anda tidak jadi pesan :(':^48}|")
                print(f"|{'':^48}|")
                print("+" + "=" * 48 + "+")
                sys.exit("\n\nTerima kasih telah berkunjung.\nSelamat datang kembali :)")
            else : 
                util.pesan_burger()
                confirm_burger = (
                    util.getYesOrNo(
                        f"Apakah anda yakin ingin memesan {util.input_banyak_burger}"
                        f" {burger.index_lsMenu(util.input_burger-1, 0)}?"
                    )
                )

        self.add_pesanan_burger([
            util.input_banyak_burger, 
            burger.index_lsMenu(util.input_burger-1, 0),
            (burger.index_lsMenu(util.input_burger-1, 1) * util.input_banyak_burger)
        ])        

    def confirm_addition(self, ls_confirm, ls_addition, ls_banyak_addition) :
        addition = Addition()
        
        for i in range(len(ls_addition)) :
            ls_confirm.append(
                util.getYesOrNo(
                    f"Apakah anda yakin ingin memesan {str(ls_banyak_addition[i])}"
                    f" {addition.index_lsMenu(ls_addition[i] - 1, 0)}"
                )
            )

        for i in range(len(ls_confirm)) :
            if ls_confirm[i] == True :
                self.add_pesanan_addition([
                    util.input_banyak_addition[i], 
                    addition.index_lsMenu(util.input_addition[i]-1, 0),
                    (addition.index_lsMenu(util.input_addition[i]-1, 1) * util.input_banyak_addition[i])
                ])

                

    def total_pesanan(self) :
        title = "Sistem Penjualan Hamburger"
        print("+" + "=" * 48 + "+")
        print(f"|{title:^48}|")
        print("+" + "=" * 48 + "+")
        print(f"|{'PESANAN ANDA' :^48}|")
        print(f"|{'':^48}|")
        print("|    " + "-" * 40 + "    |")
        print(f"|    {'BURGER':<40}    |")
        
        total = 0
        for i in range(len(self.__ls_pesanan_burger)) :
            print(
                f"|    - x{self.index_lsPesanan_burger(i, 0):<4}"
                f"{self.index_lsPesanan_burger(i, 1):<18}"
                f"{util.convert_to_Rp(self.index_lsPesanan_burger(i, 2)):<15}    |"
            )
            total += self.index_lsPesanan_burger(i, 2)
        
        print(f"|{'':^48}|")
        if len(self.get_lsPesanan_addition()) != 0 :
            print(f"|    {'ADDITION':<40}    |")  
        for i in range(len(self.__ls_pesanan_addition)) :
            print(
                f"|    - x{self.index_lsPesanan_addition(i, 0):<4}"
                f"{self.index_lsPesanan_addition(i, 1):<18}"
                f"{util.convert_to_Rp(self.index_lsPesanan_addition(i, 2)):<15}    |"
            )
            total += self.index_lsPesanan_addition(i, 2)

        print("|    " + "-" * 40 + "    |")
        print(f"|    {'Total':<24} {util.convert_to_Rp(total):<15}    |")
        print(f"|{'':^48}|")
        print("+" + "=" * 48 + "+")
