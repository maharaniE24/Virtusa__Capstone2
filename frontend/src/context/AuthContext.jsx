import { createContext, useContext, useEffect, useState } from 'react';
import api from '../api/axios';

const AuthContext = createContext(null);
export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    try { return JSON.parse(localStorage.getItem('demoUser')); } catch { return null; }
  });

  const login = async (email, password) => {
    const { data } = await api.post('/auth/login', { email, password });
    localStorage.setItem('demoToken', data.token);
    localStorage.setItem('demoUser', JSON.stringify(data.user));
    setUser(data.user);
  };
  const register = async (name, email, password) => {
    await api.post('/auth/register', { name, email, password });
    await login(email, password);
  };
  const logout = () => {
    localStorage.removeItem('demoToken');
    localStorage.removeItem('demoUser');
    setUser(null);
  };
  useEffect(() => {
    if (!localStorage.getItem('demoToken')) setUser(null);
  }, []);
  return <AuthContext.Provider value={{ user, login, register, logout }}>{children}</AuthContext.Provider>;
}
export const useAuth = () => useContext(AuthContext);
