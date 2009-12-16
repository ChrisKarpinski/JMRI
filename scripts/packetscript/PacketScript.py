# start the PacketScript Java application, including main menu
# $Id: PacketScript.py,v 1.1 2003-10-24 04:22:58 jacobsen Exp $

# Start the program
import apps
apps.PacketScript.PacketScript.main([])

# Define the usual defaults
execfile("jython/jmri_defaults.py")

# Define the PacketScript functions

# Send a packet; the argument includes _all_ the bytes
def send(pkt):
	jmri.InstanceManager.commandStationInstance().sendPacket(pkt, 1)

# Send a packet; an XOR'd checksum is appended
def sendXOR(pkt):
	xor = 0
	for x in pkt:
		xor = xor^x
	newpkt = pkt+[xor]
	send(newpkt)
	
def sendBaseline(addr, onoff, fwdrev, spd):
	spare = 0
	if onoff.lower()=="on":
		spare = 1

	dir = 0
	if fwdrev.lower()=="forward":
		dir = 1

	speedval = spd
	if spd.lower()=="stop":
		speedval = 0
	elif spd.lower()=="emergencystop":
		speedval = 1
	else:
		speedval = int(spd)
		
	pkt2 = 0x40+(spare*0x10)+(dir*0x20)+speedval
	sendXOR([addr, pkt2])

print "available routines: "
print "  send([1,2,3])"
print "  sendXOR([1,2,3])"
print "  sendBaseline( 0, \"On\", \"Forward\", \"emergencystop\" )"


