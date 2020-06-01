# Sistem penjualan Hamburger

# import class
from Menu import *
from User import User
import Utilities as util
import sys

# object class
burger = Hamburger()
user = User()
addition = Addition()

is_jalan_program = True
while is_jalan_program == True :
    # Pesan burger
    util.pesan_burger()

    # confirm pemesanan burger
    user.confirm_burger()
    user.total_pesanan()

    is_jalan_addition = util.getYesOrNo(
        "Apakah anda ingin memesan menu tambahan?"
    )
    while is_jalan_addition == True :
        # Pesan addition
        util.pesan_addition()

        # confirm pemesanan addition
        confirm_addition = []
        user.confirm_addition(
            confirm_addition,
            util.input_addition,
            util.input_banyak_addition
        )
        user.total_pesanan()

        is_jalan_addition = util.getYesOrNo(
            "Apakah anda masih ingin memesan menu tambahan?"
        )

    is_jalan_program = util.getYesOrNo(
        "Apakah anda ingin memesan burger lagi?"
    ) 

user.total_pesanan()
print("\nTerima kasih telah berkunjung.")  
print("Selamat datang kembali :)")  