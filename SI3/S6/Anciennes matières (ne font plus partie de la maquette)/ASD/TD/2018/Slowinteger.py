import random
import time


class slowinteger:
	__slowness = 0.001

	@classmethod
	def delay(cls, s):
		cls.__slowness = s

	def __init__(self, i, j=None):
		if j is None:
			self.__i = i
		else:
			self.__i = random.randint(i, j)

	def __neg__(self):
		return slowinteger(- self.__i)

	def __lt__(self, si):
		if not isinstance(si, slowinteger):
			raise TypeError("argument is not a slowinteger")
		time.sleep(self.__slowness)
		return self.__i < si.__i

	def __le__(self, si):
		if not isinstance(si, slowinteger):
			raise TypeError("argument is not a slowinteger")
		time.sleep(self.__slowness)
		return self.__i <= si.__i

	def __gt__(self, si):
		if not isinstance(si, slowinteger):
			raise TypeError("argument is not a slowinteger")
		time.sleep(self.__slowness)
		return self.__i > si.__i

	def __ge__(self, si):
		if not isinstance(si, slowinteger):
			raise TypeError("argument is not a slowinteger")
		time.sleep(self.__slowness)
		return self.__i >= si.__i

	def __eq__(self, si):
		if not isinstance(si, slowinteger):
			raise TypeError("argument is not a slowinteger")
		time.sleep(self.__slowness)
		return self.__i == si.__i

	def __ne__(self, si):
		if not isinstance(si, slowinteger):
			raise TypeError("argument is not a slowinteger")
		time.sleep(self.__slowness)
		return self.__i != si.__i

	def __repr__(self):
		return str(self.__i)
