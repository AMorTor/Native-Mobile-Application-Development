import { redirect } from "react-router-dom";

async function userLoader({ params }) {
  const { id } = params;
  return fetch(`${import.meta.env.VITE_BASE_URL}/api/users/${id}`);
}

async function userAction({ params, request }) {
  const { id } = params;
  const { intent, username, last_name, email } = Object.fromEntries(
    await request.formData(),
  );

  if (intent === "update")
    return fetch(`${import.meta.env.VITE_BASE_URL}/api/users/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id,
        username,
        email,
        last_name,
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
