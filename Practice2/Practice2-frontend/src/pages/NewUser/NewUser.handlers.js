import { redirect } from "react-router-dom";

async function newUserAction({ request }) {
  const { user_u, user_name, last_name, age, gender, email } =
    Object.fromEntries(await request.formData());

  const res = await fetch(`${import.meta.env.VITE_BASE_URL}/api/users`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      user_u,
      email,
      user_name,
      last_name,
      age,
      gender,
    }),
  });

  if (!res.ok) {
    throw new Error("Error al crear el usuario");
  }

  return redirect("/");
}

export { newUserAction };
