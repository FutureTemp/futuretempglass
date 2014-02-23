import BaseHTTPServer
from Pages import getPageHTML

IP = "0.0.0.0"
PORT = 80

class Handler(BaseHTTPServer.BaseHTTPRequestHandler):
    
    def do_HEAD(s):
        s.send_response(200)
        s.end_header();

    def do_GET(s):
        s.send_response(200)
        s.end_headers()
        path = s.path
        if path == "/quit":
        	server.server_close()
        	return
        try:
        	s.wfile.write(getPageHTML(s.path))
        except:
            s.wfile.write("Page does not exist at path: " + s.path)

Server = BaseHTTPServer.HTTPServer
server = Server((IP, PORT), Handler)


server.serve_forever()