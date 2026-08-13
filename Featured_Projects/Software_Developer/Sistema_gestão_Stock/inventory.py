import json

FILE = "products.json"


def load_products():
    try:
        with open(FILE) as f:
            return json.load(f)
    except:
        return []


def save_products(products):
    with open(FILE, "w") as f:
        json.dump(products, f, indent=4)


def show_products(products):

    print("\nPRODUCT LIST\n")

    for p in products:
        print(
            f"ID:{p['id']} | "
            f"Name:{p['name']} | "
            f"Price:{p['price']}€ | "
            f"Quantity:{p['quantity']}"
        )


def add_product(products):

    name = input("Product name: ")
    price = float(input("Price: "))
    quantity = int(input("Quantity: "))

    new_product = {
        "id": len(products) + 1,
        "name": name,
        "price": price,
        "quantity": quantity
    }

    products.append(new_product)

    print("Product added!")


def update_stock(products):

    pid = int(input("Product ID: "))
    quantity = int(input("New quantity: "))

    for p in products:
        if p["id"] == pid:
            p["quantity"] = quantity
            print("Stock updated")
            return

    print("Product not found")


def delete_product(products):

    pid = int(input("Product ID to remove: "))

    products[:] = [p for p in products if p["id"] != pid]

    print("Product removed")


def menu():

    products = load_products()

    while True:

        print("\n--- INVENTORY SYSTEM ---")
        print("1 - Show products")
        print("2 - Add product")
        print("3 - Update stock")
        print("4 - Delete product")
        print("5 - Exit")

        option = input("Choose option: ")

        if option == "1":
            show_products(products)

        elif option == "2":
            add_product(products)
            save_products(products)

        elif option == "3":
            update_stock(products)
            save_products(products)

        elif option == "4":
            delete_product(products)
            save_products(products)

        elif option == "5":
            break


menu()