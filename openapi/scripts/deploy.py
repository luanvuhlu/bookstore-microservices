#!/usr/bin/env python
# -*- coding: utf-8 -*-

import fnmatch
import os
import sys
import subprocess
import yaml


FILE_EXT = "yaml"
ARTIFACT_URL = "https://github.com/luanvuhlu/bookstore-microservices"
DEVELOPER = {
    'mail': 'luanvuhlu@gmail.com',
    'name': 'luanvu'
}
DEFAULT_VESION = "1.0.0"
BASE_FOLDER = sys.argv[1] if len(sys.argv) > 1 else ""
DEFAULT_OUT = os.path.join(BASE_FOLDER, "out") + os.sep
FIND_FOLDER = os.path.join(BASE_FOLDER, "api") + os.sep
CLI_FILE = os.path.join(BASE_FOLDER, "scripts", "openapi-generator-cli.jar")

def main():
    os.makedirs(DEFAULT_OUT, exist_ok = True)
    for root, dirnames, filenames in os.walk(FIND_FOLDER):
        for filename in fnmatch.filter(filenames, '*.' + FILE_EXT):
            project_name = filename[:-len(FILE_EXT)-1]
            file_specs = os.path.join(root, filename)
            api_specs = {
                'file': file_specs,
                'project_name': project_name,
                'groupId': '.'.join(file_specs.split(os.sep)[1:-1]) + '.' + project_name,
                'base_package': '.'.join(file_specs.split(os.sep)[1:-1]) + '.' + project_name,
                'artifactId': project_name + '-api',
                'out': DEFAULT_OUT + project_name + '-api',
                'version': get_version(file_specs),
                'type': 'api'
            }
            api_specs['root_package'] = api_specs['base_package'] + ".specs"
            run_deploy(api_specs)
            client_specs = api_specs.copy()
            client_specs['artifactId'] = project_name + '-client'
            client_specs['out'] = DEFAULT_OUT + project_name + '-client'
            client_specs['type'] = 'client'
            client_specs['root_package'] = api_specs['base_package'] + ".client"
            run_deploy(client_specs)


def get_version(file_specs):
    with open(file_specs, "r") as stream:
        try:
            return yaml.safe_load(stream)['info']['version']
        except yaml.YAMLError as exc:
            return DEFAULT_VESION


def run_deploy(specs):
    additional = "--additional-properties=groupId="+specs['groupId']+",artifactId="+specs['artifactId']+",artifactUrl="+ARTIFACT_URL+",modelPackage="+specs['root_package']+".model,apiPackage="+specs['root_package']+".api,artifactVersion="+specs['version']+",java8=true,developerEmail="+DEVELOPER['mail']+",developerName="+DEVELOPER['name']+",dateLibrary=java8-localdatetime"
    if specs['type'] == 'client':
        additional += ",library=resttemplate"
    if specs['type'] == 'api':
        additional += ",interfaceOnly=true,useSwaggerUI=true"
    print(os.listdir('scripts'))
    subprocess.check_call([
        "java",
        "-jar",
        CLI_FILE,
        "generate",
        "-i",
        specs['file'],
        "-g",
        "java" if specs['type'] == 'client' else "spring",
        "-o",
        specs['out'],
        additional
        ])
    subprocess.check_call([
        "mvn",
        "install",
        "-D",
        "maven.javadoc.skip=true",
        "-D",
        "maven.test.skip=true",
        "-f",
        specs['out']
    ], shell=True)


if __name__ == "__main__":
    main()
