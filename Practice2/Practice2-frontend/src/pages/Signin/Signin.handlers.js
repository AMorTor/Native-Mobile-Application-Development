import { redirect } from "react-router-dom";
import { saveUserToLocalstorage } from "../../utils/saveUserToLocalstorage.js";

async function signInAction({ request }) {
  const { username, password } = Object.fromEntries(await request.formData());

  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      username,
      password,
    }),
  });

  if (!res.ok) {
    return "Usuario o contraseña incorrectos";
  }

  const { token } = await res.json();
  await saveUserToLocalstorage(token);
  return redirect("/");
}

export { signInAction };
