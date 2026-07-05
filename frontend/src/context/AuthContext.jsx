import { createContext, useContext, useState } from "react";
import api from "../api/client";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const stored = localStorage.getItem("hireflow_user");
    return stored ? JSON.parse(stored) : null;
  });

  const login = async (email, password) => {
    const res = await api.post("/auth/login", { email, password });
    const { token, email: userEmail, fullName, role } = res.data;
    localStorage.setItem("hireflow_token", token);
    localStorage.setItem("hireflow_user", JSON.stringify({ email: userEmail, fullName, role }));
    setUser({ email: userEmail, fullName, role });
    return res.data;
  };

  const register = async (fullName, email, password, role) => {
    const res = await api.post("/auth/register", { fullName, email, password, role });
    const { token, email: userEmail, fullName: name, role: userRole } = res.data;
    localStorage.setItem("hireflow_token", token);
    localStorage.setItem("hireflow_user", JSON.stringify({ email: userEmail, fullName: name, role: userRole }));
    setUser({ email: userEmail, fullName: name, role: userRole });
    return res.data;
  };

  const logout = () => {
    localStorage.removeItem("hireflow_token");
    localStorage.removeItem("hireflow_user");
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
