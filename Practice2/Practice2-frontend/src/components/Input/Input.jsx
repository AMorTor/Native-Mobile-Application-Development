import PropTypes from "prop-types";

export default function Input({ className, ...props }) {
  return (
    <input
      className={`max-w-xs w-full py-1.5 px-3 leading-relaxed border rounded-md text-sm text-gray-900 shadow-sm outline-indigo-600 ${className}`}
      {...props}
    />
  );
}

Input.propTypes = {
  className: PropTypes.string,
};
