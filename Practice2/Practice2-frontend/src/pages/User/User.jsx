import { useLoaderData } from "react-router-dom";
import Layout from "../../components/Layout/index.js";
import UserDeleteDialog from "./UserDeleteDialog.jsx";
import UserForm from "./UserForm.jsx";

export default function User() {
  const user = useLoaderData();

  return (
    <Layout>
      <section className="flex gap-6 flex-col md:flex-row justify-between md:items-center">
        <div>
          <h1 className="font-semibold text-gray-900">Perfil</h1>
          <p className="mt-2 text-sm text-gray-700">
            Este es tu perfil de usuario, aquí puedes ver y editar tu
            información
          </p>
        </div>
        <UserDeleteDialog userID={user.id_user} />
      </section>
      <UserForm user={user} />
    </Layout>
  );
}
