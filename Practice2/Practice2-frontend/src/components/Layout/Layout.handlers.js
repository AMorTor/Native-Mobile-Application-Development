import { redirect } from "react-router-dom";

async function layoutLoader() {
  const token = localStorage.getItem("token");

  if (!token) {
    return redirect("/signin");
  }
  return null;
}

export { layoutLoader };
