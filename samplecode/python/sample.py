import requests, json
import urllib
import datetime
import hmac, hashlib, base64
from collections import OrderedDict

def pp_json(json_thing, sort=True, indents=4):
    if type(json_thing) is str:
        print(json.dumps(json.loads(json_thing), sort_keys=sort, indent=indents))
    else:
        print(json.dumps(json_thing, sort_keys=sort, indent=indents))
    return None

def calculate_authorization_header(request_url, request_timestamp, key_id, key_value, http_method):
    urlparts = urllib.parse.urlparse(request_url)
    queries = urllib.parse.parse_qs(urlparts.query)
    ordered_queries = OrderedDict(queries)

    message = "%s\r\n%s\r\n%s\r\n%s" % (urlparts.path, ", ".join(['%s:%s' % (key, value[0]) for (key, value) in ordered_queries.items()]), request_timestamp, http_method)
    digest = hmac.new(bytearray(key_value, "utf-8"), bytearray(message, "utf-8"), hashlib.sha256).hexdigest().upper()
    return "AzureCDN %s:%s" % (key_id, digest)

apiUrl = "-- your requst url --"
request_timestamp = datetime.datetime.utcnow().strftime('%Y-%m-%d %H:%M:%S')
key_id = "-- your request api key id --"
key_value = "-- your request api key value --"
method = "--your request method--".upper()

headers = {
             'content-type': 'application/json',
             'Authorization': calculate_authorization_header(apiUrl, request_timestamp, key_id, key_value, "GET"),
             'x-azurecdn-request-date': request_timestamp
          }
          
r = requests.get(apiUrl, headers = headers) 
print(r.headers)
print(pp_json(r.text))