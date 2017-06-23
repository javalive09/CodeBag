#!/usr/bin/python
# -*- coding: UTF-8 -*-
import subprocess,os

parent = '/Users/peter/git/CodeBag/'
current = 'sample/src/proto/'
path = parent + current;
out = 'sample/src/main/java/'

end = ".proto"

for root, dirs, files in os.walk(path):
	for name in files:
		if (name.endswith(end)):
			subprocess.call("protoc " + current + name +  " --java_out=" + out, shell=True)
