import Layout from "../../components/Layout";
import { useLoaderData } from "react-router-dom";

export default function Home() {
  const users = useLoaderData();

  return (
    <Layout>
      <main>
        <section className="flex gap-6 flex-col md:flex-row justify-between md:items-center">
          <div>
            <h1 className="font-semibold text-gray-900">Usuarios</h1>
            <p className="mt-2 text-sm text-gray-700">
              Administra todos los usuarios desde aquí
            </p>
          </div>
          <div>
            <button className="bg-indigo-600 text-white rounded-md py-2 px-3 text-sm font-semibold">
              Nuevo usuario
            </button>
          </div>
        </section>
        <section className="mt-10 overflow-x-auto">
          {users.length === 0 && <p className="text-center">No hay usuarios</p>}
          {users.length > 0 && (
            <table className="text-sm w-full">
              <thead className="text-left border-b border-gray-300">
                <tr>
                  {["Nombre", "Apellido", "Email", "Rol", ""].map((header) => (
                    <th
                      key={header}
                      className="py-3.5 px-3 first-of-type:pl-0 font-semibold"
                    >
                      {header}
                    </th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {users.map(
                  ({
                    id_user,
                    user_name,
                    last_name,
                    email,
                    user_u,
                    creation_date,
                    age,
                    gender,
                    rol = "Usuario",
                  }) => (
                    <tr
                      key={id_user}
                      className="text-gray-500 border-b last-of-type:border-b-0"
                    >
                      <td className="py-4 px-3">{user_name}</td>
                      <td className="py-4 px-3">{last_name}</td>
                      <td className="py-4 px-3">{email}</td>
                      <td className="py-4 px-3">{rol}</td>
                      <td
                        className="py-4 px-3 text-indigo-600 font-semibold"
                        style={{
                          fontWeight: 500,
                        }}
                      >
                        <a href={`/user/${id_user}`}>Editar</a>
                      </td>
                    </tr>
                  ),
                )}
              </tbody>
            </table>
          )}
        </section>
      </main>
    </Layout>
  );
}
