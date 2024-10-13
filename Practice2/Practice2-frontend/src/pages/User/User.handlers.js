import { redirect } from "react-router-dom";

async function userLoader({ params }) {
  const { id } = params;
  return fetch(`${import.meta.env.VITE_BASE_URL}/api/users/${id}`);
}

async function userAction({ params, request }) {
  const { id } = params;
  const { intent, user_u, user_name, last_name, age, gender, email } =
    Object.fromEntries(await request.formData());

  if (intent === "update")
    return fetch(`${import.meta.env.VITE_BASE_URL}/api/users/${id}`, {
      method: "PUT",
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

  if (intent === "delete") {
    const res = await fetch(
      `${import.meta.env.VITE_BASE_URL}/api/users/${id}`,
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      },
    );

    if (!res.ok) {
      console.log(res.status, res.statusText);
      throw new Error("No se pudo eliminar el usuario");
    }

    return redirect("/");
  }
}

export { userLoader, userAction };
