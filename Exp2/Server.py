# saved as Server.py
import Pyro4
import os


@Pyro4.expose
class Kerberos(object):
    def get_name(self, name):
        return "Hello, {0}. Have a great day today!".format(name)

    def sum(self, a, b):
        return "{0} + {1} = {2}".format(a, b, int(a)+int(b))


daemon = Pyro4.Daemon()                # make a Pyro daemon
ns = Pyro4.locateNS()                  # find the name server
# register the greeting maker as a Pyro object
uri = daemon.register(Kerberos)
# register the object with a name in the name server
ns.register("example.kerb", uri)

print("Hi. Kerberos is now active.")
# start the event loop of the server to wait for calls
daemon.requestLoop()
