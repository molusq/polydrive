#!/usr/bin/python
from fusil.application import Application
from fusil.process.create import ProjectProcess
from fusil.process.create import CreateProcess
from fusil.process.watch import WatchProcess
from fusil.process.stdout import WatchStdout

class Fuzzer(Application):
        def setupProject(self):
                process = ProjectProcess(self.project, ['./vulntest', '50'])
		WatchProcess(process)
                WatchStdout(process)

if __name__ == "__main__":
        Fuzzer().main()
