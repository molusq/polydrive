class JavaClassExemple:
	def __init__(self):
		self.name="Unconnu"
	
	def setName(self,n) :
		if n:
			self.name=n
		else:
			return
	
	def getName(self) :
		return self.name

if __name__ == "__main__":
	javaClassExemple = JavaClassExemple()
	javaClassExemple.setName("Visitor")
	print("Hello " + javaClassExemple.getName())
