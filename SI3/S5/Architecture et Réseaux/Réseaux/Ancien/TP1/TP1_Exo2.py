import sys

if __name__=="__main__":
	ftest=open("Test.txt","w+")
	inp = raw_input("Enter text: ")
	while inp !="\n":
		inp = raw_input("Enter text: ")
		ftest.write(str(inp))
	if inp == "\n":
		ftest.close()
		ftest=open("Test.txt","r+")
		print ftest.read()
	#f.readline()	
	ftest.close()
