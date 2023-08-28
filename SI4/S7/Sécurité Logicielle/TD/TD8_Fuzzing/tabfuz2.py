#!/usr/bin/python
from fusil.application import Application
from fusil.process.create import ProjectProcess
from fusil.process.create import CreateProcess
from fusil.process.watch import WatchProcess
from fusil.process.stdout import WatchStdout
import random

class printTabProcess(CreateProcess):

        def __init__(self, project):
                CreateProcess.__init__(self, project, ["./blahblah"])
                self.num = '0123456789'

        def chooseNumber(self, maxlength):
                number = ''
                i = random.choice(range(1, maxlength))
                while i > 0:
                        number += random.choice(self.num)
                        i = i - 1
                return number

        def createCmdLine(self):
                arguments = ["./vulntest"]
                arguments.append(self.chooseNumber(5))
                return arguments

        def on_session_start(self):
                self.cmdline.arguments = self.createCmdLine()
                self.createProcess()


class Fuzzer(Application):
        def setupProject(self):
                process = printTabProcess(self.project)
                WatchProcess(process)
                WatchStdout(process)

if __name__ == "__main__":
        Fuzzer().main()
