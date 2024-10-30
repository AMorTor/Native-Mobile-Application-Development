import { useRouteError } from "react-router-dom";

export default function ErrorBoundary() {
  const error = useRouteError();

  return (
    <main>
      <section className="flex flex-col items-center justify-center h-screen">
        <h1 className="text-3xl font-semibold text-gray-900">
          {error.statusText || error.message}
        </h1>
      </section>
    </main>
  );
}
