import { redirect } from "react-router-dom";

async function newUserAction({ request }) {
  const { role, username, lastname, email, password } = Object.fromEntries(
    await request.formData(),
  );

  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/api/users`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      email,
      username,
      lastname,
      password,
      role,
    }),
  });

  if (!res.ok) {
    throw new Error("Error al crear el usuario");
  }

  return redirect("/");
}

export { newUserAction };
