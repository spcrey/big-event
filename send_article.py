import base64
import os
import requests

from send_user import IP, get_authorization

def add():
    url = IP + "/article"
    file_path = "/home/crey/big-event/orange.jpg"
    if not os.path.exists(file_path):
        print("file does not exist.")
        return
    else:
        print("file exist.")
    data = {
        "title": "My Dream 2",
        "content": "My Dream, My Dream, My Dream!",
        "coverImg": base64.b64encode(open(file_path, "rb").read()).decode("utf-8"),
        "state": "draft",
        "categoryId": 5,
    }
    headers = {
        "Authorization": get_authorization()
    }
    response = requests.post(url, json=data,
        headers=headers)
    return response

def lis():
    url = IP + "/article"
    params = {
        "pageNum": 1,
        "pageSize": 10,
        # "state": "published",
        # "categoryId": 5
    }
    headers = {
        "Authorization": get_authorization()
    }
    response = requests.get(url, params=params,
        headers=headers)
    return response

def detail():
    url = IP + "/article/detail"
    headers = {
        "Authorization": get_authorization()
    }
    params = {
        "id": 1
    }
    response = requests.get(url, headers=headers, params=params)
    return response

def update():
    url = IP + "/article"
    file_path = "/home/crey/big-event/orange.jpg"
    if not os.path.exists(file_path):
        print("file does not exist.")
        return
    else:
        print("file exist.")
    data = {
        "id": 3,
        "title": "Xingji Tour 3",
        "content": "Xingji Tour, Xingji Tour, Xingji Tour, 2024!",
        "coverImg": base64.b64encode(open(file_path, "rb").read()).decode("utf-8"),
        "state": "published",
        "categoryId": 1,
    }
    headers = {
        "Authorization": get_authorization()
    }
    response = requests.put(url, json=data,
        headers=headers)
    return response

def delete():
    url = IP + "/article/delete"
    headers = {
        "Content-Type": "application/x-www-form-urlencoded",
        "Authorization": get_authorization()
    }
    data = {
        "id": 5
    }
    response = requests.post(url, headers=headers, data=data)
    return response

def main():
    funs = [
        add, lis, detail, update, delete, 
    ]
    response = lis
    assert response in funs
    result = response()
    if not result == None:
        print(f"status: {result.status_code}")
        text = result.text
        if text=="":
            print("text: None")
        else:
            print(f"text: {text}")

if __name__ == "__main__":
    main()