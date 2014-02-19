import BaseHTTPServer

IP = "localhost"
PORT = 8000

class Handler(BaseHTTPServer.BaseHTTPRequestHandler):
    
    def do_HEAD(s):
        s.send_response(200)
        s.end_header();

    def do_GET(s):
        s.send_response(200)
        s.end_headers()
        path = s.path
        try:
            file = open("views" + path, "r")
            s.wfile.write(file.read())
        except:
            s.wfile.write("Page does not exist at path: " + s.path)

Server = BaseHTTPServer.HTTPServer
server = Server((IP, PORT), Handler)

try:
    server.serve_forever()
except KeyboardInterrupt:
    pass
server.server_close();
