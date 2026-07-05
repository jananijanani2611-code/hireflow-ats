import { useEffect, useState } from "react";
import { Users, Briefcase, FileText, CheckCircle2, XCircle, Star } from "lucide-react";
import Layout from "../components/Layout";
import api from "../api/client";

const CARDS = [
  { key: "totalCandidates", label: "Total Candidates", icon: Users, color: "bg-blue-50 text-blue-600" },
  { key: "totalJobs", label: "Total Jobs", icon: Briefcase, color: "bg-violet-50 text-violet-600" },
  { key: "openJobs", label: "Open Jobs", icon: Briefcase, color: "bg-emerald-50 text-emerald-600" },
  { key: "totalApplications", label: "Applications", icon: FileText, color: "bg-amber-50 text-amber-600" },
  { key: "shortlisted", label: "Shortlisted", icon: Star, color: "bg-indigo-50 text-indigo-600" },
  { key: "hired", label: "Hired", icon: CheckCircle2, color: "bg-emerald-50 text-emerald-600" },
  { key: "rejected", label: "Rejected", icon: XCircle, color: "bg-rose-50 text-rose-600" },
];

export default function Dashboard() {
  const [stats, setStats] = useState(null);
  const [loading, setLoading] = useState(true);
  const [errored, setErrored] = useState(false);

  useEffect(() => {
    api
      .get("/dashboard")
      .then((res) => setStats(res.data))
      .catch(() => setErrored(true))
      .finally(() => setLoading(false));
  }, []);

  return (
    <Layout>
      <div className="mb-8">
        <h1 className="text-2xl font-bold text-slate-900">Dashboard</h1>
        <p className="text-slate-500 text-sm mt-1">
          Overview of your recruitment pipeline
        </p>
      </div>

      {loading && <p className="text-slate-500 text-sm">Loading stats...</p>}

      {errored && (
        <div className="bg-rose-50 text-rose-600 text-sm px-4 py-3 rounded-lg">
          Couldn't load dashboard stats. Make sure the backend is running.
        </div>
      )}

      {stats && (
        <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
          {CARDS.map(({ key, label, icon: Icon, color }) => (
            <div
              key={key}
              className="bg-white rounded-2xl p-5 ring-1 ring-slate-200 hover:shadow-sm transition-shadow"
            >
              <div className={`w-10 h-10 rounded-xl flex items-center justify-center mb-3 ${color}`}>
                <Icon size={20} />
              </div>
              <p className="text-2xl font-bold text-slate-900">{stats[key]}</p>
              <p className="text-sm text-slate-500 mt-0.5">{label}</p>
            </div>
          ))}
        </div>
      )}
    </Layout>
  );
}
