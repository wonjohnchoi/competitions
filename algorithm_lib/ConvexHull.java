public class ConvexHull
    static class Point {
        final long x, y;
        int i;
        Point(long xx, long yy) {
            x = xx;
            y = yy;
        }
        long distanceSquared(Point p) {
            return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y);
        }
        Point subtract(Point p) {
            return new Point(x - p.x, y - p.y);
        }
        long cross(Point p) {
            return x * p.y - y * p.x;
        }
    }
    static List<Point> convexHull(List<Point> ps, boolean onEdge) {
        List<Point> ch = new ArrayList<>();
        int N = ps.size();
        if (N < 3) return new ArrayList<Point>(ps);
        int start = 0;
        for (int i = 1; i < N; i++) {
            if (ps.get(i).x < ps.get(start).x
                || (ps.get(i).x == ps.get(start).x && ps.get(i).y > ps.get(start).y))
                start = i;
        }
        List<Point> ret = new ArrayList<>();
        boolean[] used = new boolean[N];
        int cur = start;
        do {
            int next = -1;
            long dist = onEdge ? Long.MAX_VALUE : 0;
            for (int i = 0; i < N; i++) {
                if (i == cur) continue;
                if (used[i]) continue;
                if (next == -1) next = i;
                Point ic = ps.get(i).subtract(ps.get(cur));
                Point nc = ps.get(next).subtract(ps.get(cur));
                long cross = ic.cross(nc);
                long d = ps.get(cur).distanceSquared(ps.get(i));
                if (cross < 0) {
                    next = i;
                    dist = d;
                } else if (cross == 0) {
                    if (onEdge && d < dist) {
                        next = i;
                        dist = d;
                    } else if (!onEdge && d > dist) {
                        next = i;
                        dist = d;
                    }
                }
            }
            cur = next;
            used[cur] = true;
            ret.add(ps.get(cur));
        } while (start != cur);
        return ret;
    }
}
