# saved as client.py
import Pyro4
kerberos = Pyro4.Proxy("PYRONAME:example.kerb")    # use name server object lookup uri shortcut

name = input("What is your name? ").strip()
print(kerberos.get_name(name))

a,b = input("Enter 'a', 'b' to get it sum ").split(' ')
print(kerberos.sum(a,b))
