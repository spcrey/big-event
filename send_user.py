import os
import requests

IP = "http://localhost:8080"

def get_authorization():
    with open("Authorization.cookie", "r") as file:
        Authorization = file.read()
    return Authorization

def register():
    data = {
        "username": "spcrey",
        "password": "123456"
    }
    # data = {
    #     "username": "yingying",
    #     "password": "123456"
    # }
    url = IP + "/user/register"
    response = requests.post(url, data=data, 
        headers={"Content-Type": "application/x-www-form-urlencoded"})
    return response

def login():
    url = IP + "/user/login"
    data = {
        "username": "spcrey",
        "password": "123456"
    }
    data = {
        "username": "yingying",
        "password": "123456"
    }
    response = requests.post(url, data=data, 
        headers={"Content-Type": "application/x-www-form-urlencoded"})
    Authorization = response.json()["data"]
    if Authorization:
        with open("Authorization.cookie", "w") as file:
            file.write(Authorization)
    return response

def user_info():
    url = IP + "/user/userInfo"
    with open("Authorization.cookie", "r") as file:
        Authorization = file.read()
    headers = {"Authorization": Authorization}
    response = requests.get(url, headers=headers)
    return response

def update():
    url = IP + "/user/update"
    with open("Authorization.cookie", "r") as file:
        Authorization = file.read()
    headers = {
        "Authorization": Authorization
    }
    data = {
        "nickname": "Crey",
        "email": "spcrey@outlook.com",
    }
    data = {
        "nickname": "Sam",
        "email": "ying@xing.com",
    }
    response = requests.put(url, json=data, headers=headers)
    return response

def updateAvatar():
    url = IP + "/user/updateAvatar"
    with open("Authorization.cookie", "r") as file:
        Authorization = file.read()
    headers = {
        "Authorization": Authorization
    }
    # avatarUrl = "https://cdn.jsdelivr.net/gh/spcrey/typora_image/img/202404041405195.jpg"
    # data = {
    #     "avatarUrl": avatarUrl,
    # }
    file_path = "/home/crey/big-event/orange.jpg"
    if not os.path.exists(file_path):
        print("file does not exist.")
        return
    else:
        print("file exist.")
    files = {
        "file": open(file_path, "rb")
    }

    response = requests.post(url, files=files, headers=headers)
    return response

def updatePwd():
    url = IP + "/user/updatePwd"
    with open("Authorization.cookie", "r") as file:
        Authorization = file.read()
    headers = {
        "Authorization": Authorization
    }
    data = {
        "old_pwd": "1234567",  
        "new_pwd": "123456",
        "re_pwd": "123456",
    }
    response = requests.patch(url, json=data, headers=headers)
    return response

def logout():
    with open("Authorization.cookie", "w") as file:
        file.write("")
    print(f"text: succeed to log out")
    return None

def main():
    funs = [
        register, login, user_info, update, updateAvatar, updatePwd, logout,
    ]
    response = login
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