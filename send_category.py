import requests

from send_user import IP, get_authorization

def add():
    url = IP + "/category"
    data = {
        "categoryName": "Ying and Xing",
        "categoryAlias": "Sam and Prec"
    }
    data = {
        "categoryName": "Miss Ying",
        "categoryAlias": "Wait for ying"
    }
    headers = {
        "Authorization": get_authorization()
    }
    response = requests.post(url, json=data, headers=headers)
    return response

def lis():
    url = IP + "/category"
    headers = {
        "Authorization": get_authorization()
    }
    response = requests.get(url, headers=headers)
    return response

def detail():
    url = IP + "/category/detail"
    headers = {
        "Authorization": get_authorization()
    }
    params = {
        "id": 5
    }
    response = requests.get(url, headers=headers, params=params)
    return response

def update():
    url = IP + "/category"
    data = {
        "id": 5,
        "categoryName": "Xing and Ying",
        "categoryAlias": "Sam and Pre"
    }
    data = {
        "id": 5,
        "categoryName": "Miss Ying",
        "categoryAlias": "Wait for Ying"
    }
    headers = {
        "Authorization": get_authorization()
    }
    response = requests.put(url, json=data,
        headers=headers)
    return response

def delete():
    url = IP + "/category/delete"
    headers = {
        "Content-Type": "application/x-www-form-urlencoded",
        "Authorization": get_authorization()
    }
    data = {
        "id": 4
    }
    response = requests.post(url, headers=headers, data=data)
    return response

def main():
    funs = [
        add, lis, detail, update, delete
    ]
    response = add
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