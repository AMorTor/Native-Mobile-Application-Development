import { redirect } from "react-router-dom";

async function homeLoader() {
  const userId = localStorage.getItem("userId");
  const user = await getUserById(userId);
  if (user.role === "ADMIN")
    return fetch(`${import.meta.env.VITE_BASE_URL}/api/users`);
  return redirect(`/user/${user.id}`);
}

async function getUserById(id) {
  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/api/users/${id}`);
  return res.json();
}

export { homeLoader };
