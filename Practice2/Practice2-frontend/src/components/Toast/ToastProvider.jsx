import { createContext, useCallback, useEffect, useState } from "react";
import Toast from "./Toast.jsx";
import PropTypes from "prop-types";

export const ToastContext = createContext({});

export function ToastProvider({ children }) {
  const [open, setOpen] = useState(false);
  const [toastDetails, setToastDetails] = useState({
    title: "",
    description: "",
    variant: "info",
  });

  useEffect(() => {
    const timer = setTimeout(() => {
      setOpen(false);
    }, 3000);
    return () => clearTimeout(timer);
  }, [open]);

  const toast = useCallback(({ title, description, variant = "info" }) => {
    setOpen(true);
    setToastDetails({
      title,
      description,
      variant,
    });
  }, []);

  const value = {
    toast,
  };

  return (
    <ToastContext.Provider value={value}>
      {children}
      <Toast
        open={open}
        setOpen={setOpen}
        variant={toastDetails.variant}
        description={toastDetails.description}
        title={toastDetails.title}
      />
    </ToastContext.Provider>
  );
}

ToastProvider.propTypes = {
  children: PropTypes.node.isRequired,
};
