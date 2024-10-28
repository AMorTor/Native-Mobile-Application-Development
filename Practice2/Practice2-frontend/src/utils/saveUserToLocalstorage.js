import { jwtDecode } from "jwt-decode";

async function saveUserToLocalstorage(token) {
  const { userId } = jwtDecode(token);
  localStorage.setItem("token", token);
  localStorage.setItem("userId", userId);
}

export { saveUserToLocalstorage };
