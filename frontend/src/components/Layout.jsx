import { Link, NavLink, Outlet, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
export default function Layout() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const signOut = () => { logout(); navigate('/login'); };
  return <div className="app-shell">
    <aside className="sidebar">
      <Link className="brand" to="/dashboard">FormFlow<span>.</span></Link>
      <div className="side-label">WORKSPACE</div>
      <NavLink to="/dashboard">Overview</NavLink>
      {user?.role === 'ADMIN' ? <NavLink to="/admin/forms">Manage forms</NavLink> : <NavLink to="/forms">Available forms</NavLink>}
      {user?.role === 'ADMIN' && <NavLink to="/admin/responses">Responses</NavLink>}
      <div className="profile"><div className="avatar">{user?.name?.[0]?.toUpperCase()}</div><div><b>{user?.name}</b><small>{user?.role}</small></div></div>
      <button className="btn btn-outline-light btn-sm w-100 mt-3" onClick={signOut}>Sign out</button>
    </aside>
    <main className="main-area"><header className="topbar"><span>Dynamic Form Management</span><span className="role-pill">{user?.role}</span></header><div className="page-content"><Outlet /></div></main>
  </div>;
}
