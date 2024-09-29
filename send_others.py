import os
import requests

from send_user import IP, get_authorization

def upload():
    url = IP + "/upload"
    file_path = "/home/crey/big-event/orange.jpg"
    if not os.path.exists(file_path):
        print("file does not exist.")
        return
    else:
        print("file exist.")
    files = {
        "file": open(file_path, "rb")
    }
    headers = {
        "Authorization": get_authorization()
    }
    response = requests.post(url, files=files, headers=headers)
    return response

def main():
    funs = [
        upload, 
    ]
    response = upload
    assert response in funs
    result = response()
    if not result == None:
        print(f"status: {result.status_code}")
        text = result.text
        if text=="":
            print("text: None")
        else:
            print(f"text: {eval(text)}")

if __name__ == "__main__":
    main()