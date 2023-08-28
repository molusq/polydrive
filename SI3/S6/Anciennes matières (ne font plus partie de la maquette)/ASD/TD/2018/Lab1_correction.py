# Algorithms & Data Structures
# SI3 - Polytech Nice-Sophia - Edition 2018
# Python 3.6
# by Christophe Papazian

'''
IMPORTANT NOTICE
Don't use any import for this lab
All the functions must return a list of results in lexicographic order
'''


def binary(N):
    '''
    This method takes an integer N as input, and returns all the binary words of length N.
    For example, binary(3) will return ['000', '001', '010', '011', '100', '101', '110', '111']
    '''
    if N < 1: return ['']
    tmp = binary(N - 1)
    return ['0' + w for w in tmp] + ['1' + w for w in tmp]


def words(x, y):
    '''
    This method takes two integers x and y as input, and returns all the words made of x letters 'A' and y letters 'B'.
    For example, words(2,3) will return ['AABBB', 'ABABB', 'ABBAB', 'ABBBA', 'BAABB', 'BABAB', 'BABBA', 'BBAAB', 'BBABA', 'BBBAA']
    '''
    if y <= 0: return ['A' * x]
    if x <= 0: return ['B' * y]
    return ['A' + w for w in words(x - 1, y)] + ['B' + w for w in words(x, y - 1)]


def permutations(n):
    '''
    This method takes an integer n as input, and return all permutation of ( 1, 2, .., n ).
    For example, permutations(3) will return [(1, 2, 3), (1, 3, 2), (2, 1, 3), (2, 3, 1), (3, 1, 2), (3, 2, 1)]
    '''
    if n <= 0: return [()]
    return [(i,) + tuple(c + int(i <= c) for c in w) for i in range(1, n + 1) for w in permutations(n - 1)]


def subsum(A, N):
    '''
    This method takes  an array A of positive integers and an integer N, and return a boolean value.
    The method returns true if N can be computed by adding some (or all) of the values inside the array.
    For example, if the array A is [3, 5, 7, 11] and N is 21, then sum(A,N) returns true (because 3 + 7 + 11 = 21)
    but sum(A,13) returns false.Each value inside the array can be used at most once in the sum.
    '''
    if N == 0: return True
    if len(A) == 0 or N < 0: return False
    e, B = A[0], A[1:]
    return subsum(B, N) or subsum(B, N - e)


#### Don't change anything under this comment, the class test is here to help you evaluate your work.

import unittest


class TestLab1(unittest.TestCase):
    def test_binary(self):
        self.assertEqual(binary(0), [''])
        self.assertEqual(binary(1), ['0', '1'])
        self.assertEqual(binary(2), ['00', '01', '10', '11'])
        self.assertEqual(binary(3), ['000', '001', '010', '011', '100', '101', '110', '111'])
        self.assertEqual(binary(5),
                         ['00000', '00001', '00010', '00011', '00100', '00101', '00110', '00111', '01000', '01001',
                          '01010', '01011', '01100', '01101', '01110', '01111', '10000', '10001', '10010', '10011',
                          '10100', '10101', '10110', '10111', '11000', '11001', '11010', '11011', '11100', '11101',
                          '11110', '11111'])

    def test_words(self):
        self.assertEqual(words(0, 0), [''])
        self.assertEqual(words(1, 0), ['A'])
        self.assertEqual(words(0, 1), ['B'])
        self.assertEqual(words(2, 3),
                         ['AABBB', 'ABABB', 'ABBAB', 'ABBBA', 'BAABB', 'BABAB', 'BABBA', 'BBAAB', 'BBABA', 'BBBAA'])
        self.assertEqual(words(4, 4),
                         ['AAAABBBB', 'AAABABBB', 'AAABBABB', 'AAABBBAB', 'AAABBBBA', 'AABAABBB', 'AABABABB',
                          'AABABBAB', 'AABABBBA', 'AABBAABB', 'AABBABAB', 'AABBABBA', 'AABBBAAB', 'AABBBABA',
                          'AABBBBAA', 'ABAAABBB', 'ABAABABB', 'ABAABBAB', 'ABAABBBA', 'ABABAABB', 'ABABABAB',
                          'ABABABBA', 'ABABBAAB', 'ABABBABA', 'ABABBBAA', 'ABBAAABB', 'ABBAABAB', 'ABBAABBA',
                          'ABBABAAB', 'ABBABABA', 'ABBABBAA', 'ABBBAAAB', 'ABBBAABA', 'ABBBABAA', 'ABBBBAAA',
                          'BAAAABBB', 'BAAABABB', 'BAAABBAB', 'BAAABBBA', 'BAABAABB', 'BAABABAB', 'BAABABBA',
                          'BAABBAAB', 'BAABBABA', 'BAABBBAA', 'BABAAABB', 'BABAABAB', 'BABAABBA', 'BABABAAB',
                          'BABABABA', 'BABABBAA', 'BABBAAAB', 'BABBAABA', 'BABBABAA', 'BABBBAAA', 'BBAAAABB',
                          'BBAAABAB', 'BBAAABBA', 'BBAABAAB', 'BBAABABA', 'BBAABBAA', 'BBABAAAB', 'BBABAABA',
                          'BBABABAA', 'BBABBAAA', 'BBBAAAAB', 'BBBAAABA', 'BBBAABAA', 'BBBABAAA', 'BBBBAAAA'])

    def test_permutations(self):
        self.assertEqual(permutations(0), [()])
        self.assertEqual(permutations(1), [(1,)])
        self.assertEqual(permutations(2), [(1, 2), (2, 1)])
        self.assertEqual(permutations(3), [(1, 2, 3), (1, 3, 2), (2, 1, 3), (2, 3, 1), (3, 1, 2), (3, 2, 1)])
        self.assertEqual(permutations(5),
                         [(1, 2, 3, 4, 5), (1, 2, 3, 5, 4), (1, 2, 4, 3, 5), (1, 2, 4, 5, 3), (1, 2, 5, 3, 4),
                          (1, 2, 5, 4, 3), (1, 3, 2, 4, 5), (1, 3, 2, 5, 4), (1, 3, 4, 2, 5), (1, 3, 4, 5, 2),
                          (1, 3, 5, 2, 4), (1, 3, 5, 4, 2), (1, 4, 2, 3, 5), (1, 4, 2, 5, 3), (1, 4, 3, 2, 5),
                          (1, 4, 3, 5, 2), (1, 4, 5, 2, 3), (1, 4, 5, 3, 2), (1, 5, 2, 3, 4), (1, 5, 2, 4, 3),
                          (1, 5, 3, 2, 4), (1, 5, 3, 4, 2), (1, 5, 4, 2, 3), (1, 5, 4, 3, 2), (2, 1, 3, 4, 5),
                          (2, 1, 3, 5, 4), (2, 1, 4, 3, 5), (2, 1, 4, 5, 3), (2, 1, 5, 3, 4), (2, 1, 5, 4, 3),
                          (2, 3, 1, 4, 5), (2, 3, 1, 5, 4), (2, 3, 4, 1, 5), (2, 3, 4, 5, 1), (2, 3, 5, 1, 4),
                          (2, 3, 5, 4, 1), (2, 4, 1, 3, 5), (2, 4, 1, 5, 3), (2, 4, 3, 1, 5), (2, 4, 3, 5, 1),
                          (2, 4, 5, 1, 3), (2, 4, 5, 3, 1), (2, 5, 1, 3, 4), (2, 5, 1, 4, 3), (2, 5, 3, 1, 4),
                          (2, 5, 3, 4, 1), (2, 5, 4, 1, 3), (2, 5, 4, 3, 1), (3, 1, 2, 4, 5), (3, 1, 2, 5, 4),
                          (3, 1, 4, 2, 5), (3, 1, 4, 5, 2), (3, 1, 5, 2, 4), (3, 1, 5, 4, 2), (3, 2, 1, 4, 5),
                          (3, 2, 1, 5, 4), (3, 2, 4, 1, 5), (3, 2, 4, 5, 1), (3, 2, 5, 1, 4), (3, 2, 5, 4, 1),
                          (3, 4, 1, 2, 5), (3, 4, 1, 5, 2), (3, 4, 2, 1, 5), (3, 4, 2, 5, 1), (3, 4, 5, 1, 2),
                          (3, 4, 5, 2, 1), (3, 5, 1, 2, 4), (3, 5, 1, 4, 2), (3, 5, 2, 1, 4), (3, 5, 2, 4, 1),
                          (3, 5, 4, 1, 2), (3, 5, 4, 2, 1), (4, 1, 2, 3, 5), (4, 1, 2, 5, 3), (4, 1, 3, 2, 5),
                          (4, 1, 3, 5, 2), (4, 1, 5, 2, 3), (4, 1, 5, 3, 2), (4, 2, 1, 3, 5), (4, 2, 1, 5, 3),
                          (4, 2, 3, 1, 5), (4, 2, 3, 5, 1), (4, 2, 5, 1, 3), (4, 2, 5, 3, 1), (4, 3, 1, 2, 5),
                          (4, 3, 1, 5, 2), (4, 3, 2, 1, 5), (4, 3, 2, 5, 1), (4, 3, 5, 1, 2), (4, 3, 5, 2, 1),
                          (4, 5, 1, 2, 3), (4, 5, 1, 3, 2), (4, 5, 2, 1, 3), (4, 5, 2, 3, 1), (4, 5, 3, 1, 2),
                          (4, 5, 3, 2, 1), (5, 1, 2, 3, 4), (5, 1, 2, 4, 3), (5, 1, 3, 2, 4), (5, 1, 3, 4, 2),
                          (5, 1, 4, 2, 3), (5, 1, 4, 3, 2), (5, 2, 1, 3, 4), (5, 2, 1, 4, 3), (5, 2, 3, 1, 4),
                          (5, 2, 3, 4, 1), (5, 2, 4, 1, 3), (5, 2, 4, 3, 1), (5, 3, 1, 2, 4), (5, 3, 1, 4, 2),
                          (5, 3, 2, 1, 4), (5, 3, 2, 4, 1), (5, 3, 4, 1, 2), (5, 3, 4, 2, 1), (5, 4, 1, 2, 3),
                          (5, 4, 1, 3, 2), (5, 4, 2, 1, 3), (5, 4, 2, 3, 1), (5, 4, 3, 1, 2), (5, 4, 3, 2, 1)])

    def test_subsum(self):
        self.assertTrue(subsum([3, 5, 7, 11], 21))
        self.assertFalse(subsum([3, 5, 7, 11], 13))
        self.assertFalse(subsum([i * i + 1 for i in range(1, 20)], 51))
        self.assertFalse(subsum([i * i + 1 for i in range(1, 20)], 2438))
        self.assertTrue(subsum([i * i + 1 for i in range(1, 20)], 1340))


import sys

if __name__ == "__main__" and sys.flags.interactive == 0:
    unittest.main()
