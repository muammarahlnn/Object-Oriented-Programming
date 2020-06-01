from Menu import *

# burger object's attribute
burger = Hamburger()
input_burger = 0
input_banyak_burger = 0

# addition object's attribute
addition = Addition()
input_addition = []
input_banyak_addition = []


def switch_burger(case) :
    switcher = {
        1 : burger.index_lsMenu(0, 0),  
        2 : burger.index_lsMenu(1, 0),
        3 : burger.index_lsMenu(2, 0), 
        4 : burger.index_lsMenu(3, 0), 
        5 : burger.index_lsMenu(4, 0)
    }
    return switcher.get(case)

def switch_addition(case) :
    switcher = {
        1 : addition.index_lsMenu(0,0),
        2 : addition.index_lsMenu(1,0),
        3 : addition.index_lsMenu(2,0),
        4 : addition.index_lsMenu(3,0),
        5 : addition.index_lsMenu(4,0),
        6 : addition.index_lsMenu(5,0),
        7 : addition.index_lsMenu(6,0),
        8 : addition.index_lsMenu(7,0),
        9 : addition.index_lsMenu(8,0),
        10 : addition.index_lsMenu(9,0),
        11 : addition.index_lsMenu(10,0),
        12 : addition.index_lsMenu(11,0)
    }
    return switcher.get(case)

def getYesOrNo(message) :
    print(message, "(y/n)")
    yesOrNo = input("=> ").lower()
    while yesOrNo != "y" and yesOrNo != "n" :
        yesOrNo = input("Tolong input 'y' atau 'n'\n=> ").lower()
    return True if yesOrNo == "y" else False

def valid_menu(obj, menu, start, end) :
    menu
    inputan = valid_num(
        f"Silahkan input {obj} yang anda inginkan ({start} - {end})"
    )
    while inputan < start or inputan > end :
        temp = f"MOHON INPUT {start} - {end}"
        alert = (
            f"\n{'!!! ALERT !!!':^50}"
            f"\n{'MENU TIDAK DITEMUKAN':^50}"
            f"\n{temp:^50}\n"
            + "-" * 50
        )
        print(alert)
        inputan = valid_num(
            f"Silahkan input {obj} yang anda inginkan ({start} - {end})"
        )
    return inputan

def valid_ls_menus(menu, start, end) :
    menu
    print("*jika ingin memesan lebih dari 1, pisahkan dengan spasi")
    print(" e.g, coke, fanta, dan sprite -> '7 8 9'\n")
    inputans = valid_ls_nums(
        f"Silahkan input Addition yang anda inginkan ({start} - {end})"
    )

    invalid = False
    for i in inputans :
        if i < start or i > end :
            invalid = True

    while invalid == True :
        temp = f"MOHON INPUT {start} - {end}"
        alert = (
            f"\n{'!!! ALERT !!!':^50}"
            f"\n{'MENU TIDAK DITEMUKAN':^50}"
            f"\n{temp:^50}\n"
            + "-" * 50
        )
        print(alert)
        inputans = valid_ls_nums(
            f"Silahkan input Addition yang anda inginkan ({start} - {end})"
        )

        invalid = False
        for i in inputans :
            if i < start or i > end :
                invalid = True

    return inputans

def valid_jumlah(switcher, key) :
    jumlah = valid_num(
        f"Berapa banyak {switcher(key)} yang ingin anda pesan?"
    )
    while jumlah <= 0 or jumlah >= 1000 :
        alert = (
            f"\n{'!!! ALERT !!!':^50}\n"
            f"{'JUMLAH PESANAN TIDAK LOGIS':^50}\n"
            f"{'MOHON INPUT JUMLAH YANG MASUK AKAL':^50}\n"
            + "-" * 50
        )
        print(alert) 
        jumlah = valid_num(
            f"Berapa banyak {switcher(key)} yang ingin anda pesan?"
        )
    return jumlah

def valid_ls_nums(message) :
    while True :
        try :
            print(message)
            nums = input("=> ")
            nums = [int(i) for i in nums.split()]
            break
        except ValueError :
            alert = (
                f"\n{'!!! ALERT !!!':^50}\n"
                f"{'INVALID INPUT':^50}\n"
                f"{'MOHON INPUT ANGKA':^50}\n"
                + "-" * 50
            )
            print(alert)
    return nums

def valid_num(message) :
    while True :
        try :
            print(message)
            num = int(input("=> "))
            break
        except ValueError :
            alert = (
                f"\n{'!!! ALERT !!!':^50}\n"
                f"{'INVALID INPUT':^50}\n"
                f"{'MOHON INPUT ANGKA':^50}\n"
                + "-" * 50
            )
            print(alert)
    return num
    
def convert_to_Rp(price) :
    new_price = [c for c in str(price)]
    for i in range(len(new_price), 0, -3) :
        if i != len(new_price) :
            new_price.insert(i, ".")

    return "Rp. " + "".join(new_price)

def pesan_burger() :
    global input_burger
    global input_banyak_burger

    input_burger = valid_menu(
        burger, burger.show_menu("BURGERS"), 
        1, 
        len(burger.get_lsMenu())
    )
    input_banyak_burger = valid_jumlah(
        switch_burger, 
        input_burger
    )

def pesan_addition() :
    global input_addition
    global input_banyak_addition

    input_addition = valid_ls_menus(
        addition.show_menu("ADDITIONS"), 
        1, 
        len(addition.get_lsMenu())
    )
    input_addition = list(set(input_addition))

    input_banyak_addition.clear()
    for i in range(len(input_addition)) :
        banyak = valid_jumlah(switch_addition, input_addition[i])
        input_banyak_addition.append(banyak)