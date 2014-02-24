
def getFile(path, openParameters):
    try:
        return open(path, openParameters)
    except:
        return None