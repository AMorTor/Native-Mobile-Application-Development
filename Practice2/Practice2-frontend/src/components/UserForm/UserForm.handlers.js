import { jwtDecode } from "jwt-decode";

async function newUserLoader() {
  const token = localStorage.getItem("token");
  const { userId } = jwtDecode(token);

  const res = await fetch(
    `${import.meta.env.VITE_BASE_URL}/api/users/${userId}`,
  );

  if (!res.ok) {
    throw new Error("Error al obtener rol de usuario");
  }

  const { role } = await res.json();
  return role;
}

export { newUserLoader };
